<?php

namespace App\Form;

use App\Entity\Users;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Captcha\Bundle\CaptchaBundle\Form\Type\CaptchaType;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints \ValidCaptcha;


class ConnexionUserType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('email')

            ->add('password',PasswordType::class)
            ->add ("captchaCode",CaptchaType::class,[
                'captchaConfig' => 'ExampleCaptcha',
                'constraints' => [
                    new ValidCaptcha([
                        'message'=> 'Invalid captcha , pelase try again '
                    ])
                ]

            ])
        ;



    }


    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Users::class,
        ]);
    }

}
