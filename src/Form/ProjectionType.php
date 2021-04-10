<?php

namespace App\Form;

use App\Entity\Projection;
use phpDocumentor\Reflection\Types\String_;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\FormTypeInterface;

class ProjectionType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom')
            ->add('genre', ChoiceType::class, [
                'choices'  => [
                    'comique' => String_::class,
                    'horreur' =>  String_::class,
                    'action' =>  String_::class,
                    'aventure' =>  String_::class,
                    'science-fiction' =>  String_::class,
                    'mystere' =>  String_::class,
                    'histoire' =>  String_::class,
                ],
            ])
            ->add('ageRecommande')
            ->add('duree')
            ->add('image')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Projection::class,
        ]);
    }
}
