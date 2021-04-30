<?php

namespace App\Form;

use App\Entity\ProgrammerFilm;
use App\Entity\Projection;
use Doctrine\ORM\Mapping\Entity;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ProgrammerFilmType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('date_projection')
            ->add('langue')
            ->add('projection',EntityType::class,['class'=>Projection::class,'choice_label'=>'nom'])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => ProgrammerFilm::class,
        ]);
    }
}
